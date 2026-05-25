package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import storage.Warehouse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.*;

class ResourceValidationTest {

    private final ClassLoader classpath = getClass().getClassLoader();

    @Test
    @DisplayName("CSV: проверка товаров из data.csv")
    void verifyCsvProducts() throws Exception {
        byte[] archive = extractZipContent("documents.zip");

        try (ByteArrayInputStream bais = new ByteArrayInputStream(archive);
             ZipInputStream zipper = new ZipInputStream(bais)) {

            ZipEntry entry;
            while ((entry = zipper.getNextEntry()) != null) {
                if (!entry.getName().equals("data.csv")) continue;

                byte[] csvBytes = readZipEntryFully(zipper);
                String content = new String(csvBytes, StandardCharsets.UTF_8);
                String[] lines = content.split("\n");

                assertEquals("id,product,price", lines[0].trim());
                String[] firstRow = lines[1].split(",");
                assertEquals("1", firstRow[0]);
                assertEquals("Ноутбук", firstRow[1]);
                assertEquals("50000", firstRow[2].trim());
                assertTrue(content.contains("Клавиатура,3000"));

                zipper.closeEntry();
                return;
            }
            fail("Файл data.csv не найден в архиве");
        }
    }

    @Test
    @DisplayName("XLSX: проверка номера счёта из Payments.xlsx")
    void verifyExcelAccountNumber() throws Exception {
        byte[] archive = extractZipContent("documents.zip");

        try (ByteArrayInputStream bais = new ByteArrayInputStream(archive);
             ZipInputStream zipper = new ZipInputStream(bais)) {

            ZipEntry entry;
            while ((entry = zipper.getNextEntry()) != null) {
                if (!entry.getName().equals("Payments.xlsx")) continue;

                byte[] excelBytes = readZipEntryFully(zipper);
                try (ByteArrayInputStream eis = new ByteArrayInputStream(excelBytes);
                     Workbook wb = WorkbookFactory.create(eis)) {

                    Sheet sheet = wb.getSheetAt(0);
                    boolean found = false;
                    for (Row row : sheet) {
                        for (Cell cell : row) {
                            String cellValue = cell.getCellType() == CellType.STRING
                                    ? cell.getStringCellValue()
                                    : String.valueOf((long) cell.getNumericCellValue());
                            if (cellValue.contains("42301810158742897803")) {
                                found = true;
                                break;
                            }
                        }
                        if (found) break;
                    }
                    assertTrue(found, "Номер счёта не найден в Excel");
                }
                zipper.closeEntry();
                return;
            }
            fail("Файл Payments.xlsx не найден в архиве");
        }
    }

    @Test
    @DisplayName("PDF: проверка текста платёжного поручения")
    void verifyPdfPaymentDocument() throws Exception {
        byte[] archive = extractZipContent("documents.zip");

        try (ByteArrayInputStream bais = new ByteArrayInputStream(archive);
             ZipInputStream zipper = new ZipInputStream(bais)) {

            ZipEntry entry;
            while ((entry = zipper.getNextEntry()) != null) {
                if (!entry.getName().equals("transaction.pdf")) continue;

                byte[] pdfBytes = readZipEntryFully(zipper);
                try (PDDocument doc = PDDocument.load(pdfBytes)) {
                    PDFTextStripper stripper = new PDFTextStripper();
                    String text = stripper.getText(doc);

                    assertTrue(text.contains("ПЛАТЕЖНОЕ ПОРУЧЕНИЕ"), "Не найдено 'ПЛАТЕЖНОЕ ПОРУЧЕНИЕ'");
                    assertTrue(text.contains("САНКТ-ПЕТЕРБУРГ"), "Не найдено название банка");
                    assertTrue(text.contains("Solntsev Andrei"), "Не найдено имя плательщика");
                    assertTrue(text.contains("40820810590550000146"), "Не найден номер счёта");
                    assertTrue(text.contains("044030790"), "Не найден БИК");
                }
                zipper.closeEntry();
                return;
            }
            fail("Файл transaction.pdf не найден в архиве");
        }
    }


    @Test
    @DisplayName("JSON: парсинг склада с массивом товаров")
    void validateJsonWarehouse() throws Exception {

        try (InputStream is = classpath.getResourceAsStream("warehouse_data.json")) {
            assertNotNull(is, "Файл warehouse_data.json не найден в resources");

            ObjectMapper mapper = new ObjectMapper();
            Warehouse facility = mapper.readValue(is, Warehouse.class);


            assertEquals("WH-8899", facility.getWarehouseCode());
            assertEquals("Алексей Смирнов", facility.getManager());
            assertEquals(3, facility.getStockItems().size());


            var laptop = facility.getStockItems().get(0);
            assertEquals(1001, laptop.getArticleId());
            assertEquals("Ноутбук Lenovo ThinkPad", laptop.getProductLabel());
            assertEquals(78500.50, laptop.getUnitPrice(), 0.01);
            assertEquals(12, laptop.getQuantityAvailable());
            assertTrue(laptop.isActive(), "Ноутбук должен быть в наличии");


            var keyboard = facility.getStockItems().get(2);
            assertEquals(1003, keyboard.getArticleId());
            assertEquals(0, keyboard.getQuantityAvailable());
            assertFalse(keyboard.isActive(), "Клавиатура не должна быть в наличии");
        }
    }
    private byte[] extractZipContent(String resourceName) throws Exception {
        try (InputStream is = classpath.getResourceAsStream(resourceName);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            assertNotNull(is, "Архив " + resourceName + " не найден в resources");
            is.transferTo(baos);
            return baos.toByteArray();
        }
    }

    private byte[] readZipEntryFully(ZipInputStream zis) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        int len;
        while ((len = zis.read(buffer)) > 0) {
            baos.write(buffer, 0, len);
        }
        return baos.toByteArray();
    }

    private String loadTextResource(String name) throws Exception {
        try (InputStream is = classpath.getResourceAsStream(name);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            assertNotNull(is, "Файл " + name + " не найден в resources");
            is.transferTo(baos);
            return baos.toString(StandardCharsets.UTF_8);
        }
    }
}
