package serviceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import service.PdfGenerationService;

@Service("PdfGenerationService")
public class PdfGenerationServiceImpl implements PdfGenerationService {

    @Override
    
    public File generateWorkOrderReport(long workOrderId) {
        PdfWriter writer = null;
        try {
            Document document = new Document();
            String fileName = "WorkOrderReport.pdf";

            writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            generateCoverPage(document, writer);
            generateContentsPage(document);
            generateWoInfo(document);
            generateSectionPages(document);
            
            document.close();
            File file = new File(fileName);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        return null;
    }

    private void generateCoverPage(Document document, PdfWriter writer) throws DocumentException, IOException {
        Phrase dTypePhrase = new Phrase("SIM Lab Report", new Font(FontFamily.TIMES_ROMAN, 14f, Font.NORMAL));
        Phrase dTitlePhrase = new Phrase("Work Order - Placeholder Number", new Font(FontFamily.TIMES_ROMAN, 16f, Font.BOLD));
        Rectangle page = document.getPageSize();
        PdfPTable coverPage = new PdfPTable(1);
        PdfPCell cell = new PdfPCell();
        cell.setPhrase(dTypePhrase);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        coverPage.addCell(cell);
        cell = new PdfPCell();
        cell.setPhrase(dTitlePhrase);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        coverPage.addCell(cell);
        coverPage.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
        coverPage.writeSelectedRows(0, -1, document.leftMargin(), ((page.getHeight() / 2) + 25), writer.getDirectContent());

        document.newPage();
    }

    private void generateWoInfo(Document document) throws DocumentException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yy");
        float[] pointColumnWidths = { 150F, 150F };
        PdfPTable table = new PdfPTable(pointColumnWidths);

        PdfPCell cell = new PdfPCell();
        cell.setPhrase(new Phrase("1 Work Order Details", new Font(FontFamily.TIMES_ROMAN, 14f, Font.BOLD)));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setMinimumHeight(25);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);

        table.addCell(getWoInfoTableNameColumn("Work Order No"));
        table.addCell(getWoInfoTableValueColumn("Placeholder Number"));

        table.addCell(getWoInfoTableNameColumn("Work Order Raised By"));
        table.addCell(getWoInfoTableValueColumn("Placeholder Name"));

        table.addCell(getWoInfoTableNameColumn("Work Order Raised On"));
        table.addCell(getWoInfoTableValueColumn(simpleDateFormat.format(System.currentTimeMillis())));

        table.addCell(getWoInfoTableNameColumn("Sample Received Date"));
        table.addCell(getWoInfoTableValueColumn(simpleDateFormat.format(System.currentTimeMillis())));

        table.addCell(getWoInfoTableNameColumn("SIM Card Manufacturer"));
        table.addCell(getWoInfoTableValueColumn("Placeholder Manufacturer"));

        table.addCell(getWoInfoTableNameColumn("Testing Period"));
        table.addCell(getWoInfoTableValueColumn("10th Sep 2020 - 15th Sep 2020"));

        table.addCell(getWoInfoTableNameColumn("Testing Method"));
        table.addCell(getWoInfoTableValueColumn("Stress Testing"));

        table.addCell(getWoInfoTableNameColumn("Test Result(s)"));
        table.addCell(getWoInfoTableValueColumn("Please refer to next pages(s)"));

        float[] pointColumnWidthParagraph = { 300F };
        PdfPTable secondTable = new PdfPTable(pointColumnWidthParagraph);

        cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setMinimumHeight(50);
        secondTable.addCell(cell);
        document.newPage();
        cell = new PdfPCell();
        cell.setPhrase(new Phrase("1.1 Test Requested", new Font(FontFamily.TIMES_ROMAN, 13f, Font.BOLD)));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setMinimumHeight(25);
        secondTable.addCell(cell);

        cell = new PdfPCell();
        cell.setPhrase(new Phrase("In this work order Airtel SIM Lab is intending to perform the complete Offline and Online testing on above metioned BATCH Cards received from xxx circle.", new Font(FontFamily.TIMES_ROMAN, 13f, Font.NORMAL)));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setMinimumHeight(25);
        secondTable.addCell(cell);

        cell = new PdfPCell();
        cell.setPhrase(new Phrase("1.2 Test Cases Selected", new Font(FontFamily.TIMES_ROMAN, 13f, Font.BOLD)));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setMinimumHeight(25);
        secondTable.addCell(cell);

        cell = new PdfPCell();
        cell.setPhrase(new Phrase("Following test cases were selected based on work order type",
                new Font(FontFamily.TIMES_ROMAN, 13f, Font.NORMAL)));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setMinimumHeight(25);
        secondTable.addCell(cell);

        // Adding Table to document
        document.add(table);
        document.add(secondTable);

        Font font = new Font(FontFamily.TIMES_ROMAN, 13f, Font.NORMAL);
        List testCaseList = new List(12);
        testCaseList.setListSymbol("\u2022");

        testCaseList.add(new ListItem(30, "Placeholder Test Case 1", font));
        testCaseList.add(new ListItem(30, "Placeholder Test Case 2", font));

        testCaseList.setIndentationLeft(90);
        document.add(testCaseList);
    }
//---------------------------------------------------------------------------------------------------
    private void generateContentsPage(Document document) throws DocumentException {
        document.newPage();
        Font titleFont = new Font(FontFamily.TIMES_ROMAN, 14f, Font.BOLD);
        Font contentFont = new Font(FontFamily.TIMES_ROMAN, 12f, Font.NORMAL);

        Phrase titlePhrase = new Phrase("Contents", titleFont);
        document.add(titlePhrase);

        PdfPTable contentsTable = new PdfPTable(2);
        contentsTable.setWidthPercentage(100);
        contentsTable.setWidths(new float[] { 4, 1 });

        addContentEntry(contentsTable, "1 Work Order Details", "3", contentFont);
        addContentEntry(contentsTable, "1.1 Test Requested", "4", contentFont);
        addContentEntry(contentsTable, "1.2 Test Case Selection", "5", contentFont);
        addContentEntry(contentsTable, "1.3 SIM Details", "6", contentFont);
        addContentEntry(contentsTable, "2 Testing", "7", contentFont);
        addContentEntry(contentsTable, "2.1 Vital Testing", "7", contentFont);
        addContentEntry(contentsTable, "2.2 Physical Testing", "8", contentFont);
        addContentEntry(contentsTable, "2.2.1 Test Procedure", "8", contentFont);
        addContentEntry(contentsTable, "2.2.2 Card Thickness Testing", "8", contentFont);
        addContentEntry(contentsTable, "2.2.3 Go-No-Go (3FF & 4FF) Testing", "9", contentFont);
        addContentEntry(contentsTable, "2.3 Constant Voltage Stress Testing", "11", contentFont);
        addContentEntry(contentsTable, "2.3.1 Applications & Necessary DF on the Card", "11", contentFont);
        addContentEntry(contentsTable, "2.3.2 Test Procedure", "12", contentFont);
        addContentEntry(contentsTable, "2.3.3 Action Performed on SIM", "12", contentFont);
        addContentEntry(contentsTable, "2.3.4 Card State After Action Performed", "12", contentFont);
        addContentEntry(contentsTable, "2.4 Voltage Testing on Class A, B and C", "13", contentFont);
        addContentEntry(contentsTable, "2.4.1 Test Procedure", "13", contentFont);
        addContentEntry(contentsTable, "2.4.2 Testing Details", "13", contentFont);
        addContentEntry(contentsTable, "2.5 Profile Conformance against latest STANDARD Profile", "14", contentFont);
        addContentEntry(contentsTable, "2.5.1 Test Procedure", "14", contentFont);
        addContentEntry(contentsTable, "2.6 Network Latching Sequence", "14", contentFont);
        addContentEntry(contentsTable, "2.7 DSA Testing", "15", contentFont);
        addContentEntry(contentsTable, "2.7.1 Testing Details", "15", contentFont);
        addContentEntry(contentsTable, "2.8 Online Testing in Real Case Scenario", "16", contentFont);
        addContentEntry(contentsTable, "2.8.1 Testing Details", "16", contentFont);
        addContentEntry(contentsTable, "2.9 DSTK Testing", "17", contentFont);
        addContentEntry(contentsTable, "2.9.1 Testing Details", "17", contentFont);
        addContentEntry(contentsTable, "2.10 OTA Testing", "23", contentFont);
        addContentEntry(contentsTable, "2.10.1 Testing Details", "23", contentFont);
        addContentEntry(contentsTable, "3 SIM Card Pics", "26", contentFont);
        addContentEntry(contentsTable, "4 Conclusion", "27", contentFont);
        addContentEntry(contentsTable, "Note", "27", contentFont);

        document.add(contentsTable);
        document.newPage();
    }
    private void addContentEntry(PdfPTable table, String entry, String pageNumber, Font font) {
        PdfPCell entryCell = new PdfPCell(new Phrase(entry, font));
        entryCell.setBorder(Rectangle.NO_BORDER);
        entryCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(entryCell);

        PdfPCell pageCell = new PdfPCell(new Phrase(pageNumber, font));
        pageCell.setBorder(Rectangle.NO_BORDER);
        pageCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(pageCell);
    }
    //-----------------------------------------------------------------------------------------------
    private void generateSectionPages(Document document) throws DocumentException {
        addSectionPage(document, "2 Testing", "Placeholder for Testing Section");
        addSectionPage(document, "2.1 Vital Testing", "Placeholder for Vital Testing Section");
        addSectionPage(document, "2.2 Physical Testing", "Placeholder for Physical Testing Section");
        addSectionPage(document, "2.2.1 Test Procedure", "Placeholder for Test Procedure Section");
        addSectionPage(document, "2.2.2 Card Thickness Testing", "Placeholder for Card Thickness Testing Section");
        addSectionPage(document, "2.2.3 Go-No-Go (3FF & 4FF) Testing", "Placeholder for Go-No-Go (3FF & 4FF) Testing Section");
        addSectionPage(document, "2.3 Constant Voltage Stress Testing", "Placeholder for Constant Voltage Stress Testing Section");
        addSectionPage(document, "2.3.1 Applications & Necessary DF on the Card", "Placeholder for Applications & Necessary DF on the Card Section");
        addSectionPage(document, "2.3.2 Test Procedure", "Placeholder for Test Procedure Section");
        addSectionPage(document, "2.3.3 Action Performed on SIM", "Placeholder for Action Performed on SIM Section");
        addSectionPage(document, "2.3.4 Card State After Action Performed", "Placeholder for Card State After Action Performed Section");
        addSectionPage(document, "2.4 Voltage Testing on Class A B and C", "Placeholder for Voltage Testing on Class A B and C Section");
        addSectionPage(document, "2.4.1 Test Procedure", "Placeholder for Test Procedure Section");
        addSectionPage(document, "2.4.2 Testing Details", "Placeholder for Testing Details Section");
        addSectionPage(document, "2.5 Profile Conformance against latest STANDARD Profile", "Placeholder for Profile Conformance against latest STANDARD Profile Section");
        addSectionPage(document, "2.5.1 Test Procedure", "Placeholder for Test Procedure Section");
        addSectionPage(document, "2.6 Network Latching Sequence", "Placeholder for Network Latching Sequence Section");
        addSectionPage(document, "2.7 DSA Testing", "Placeholder for DSA Testing Section");
        addSectionPage(document, "2.7.1 Testing Details", "Placeholder for Testing Details Section");
        addSectionPage(document, "2.8 Online Testing in Real Case Scenario", "Placeholder for Online Testing in Real Case Scenario Section");
        addSectionPage(document, "2.8.1 Testing Details", "Placeholder for Testing Details Section");
        addSectionPage(document, "2.9 DSTK Testing", "Placeholder for DSTK Testing Section");
        addSectionPage(document, "2.9.1 Test Procedure", "Placeholder for Test Procedure Section");
        addSectionPage(document, "2.9.2 Applications & Necessary DF on the Card", "Placeholder for Applications & Necessary DF on the Card Section");
        addSectionPage(document, "2.9.3 Action Performed on SIM", "Placeholder for Action Performed on SIM Section");
        addSectionPage(document, "2.9.4 Card State After Action Performed", "Placeholder for Card State After Action Performed Section");
    }

    private void addSectionPage(Document document, String sectionTitle, String placeholderText) throws DocumentException {
        document.newPage();
        Phrase sectionPhrase = new Phrase(sectionTitle, new Font(FontFamily.TIMES_ROMAN, 14f, Font.BOLD));
        document.add(sectionPhrase);
        document.add(new Phrase("\n\n" + placeholderText));
    }

    private static PdfPCell getWoInfoTableNameColumn(String text) {
        PdfPCell cell = new PdfPCell();
        cell.setPhrase(new Phrase(" " + text, new Font(FontFamily.TIMES_ROMAN, 12f, Font.NORMAL)));
        cell.setMinimumHeight(25);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }

    private static PdfPCell getWoInfoTableValueColumn(String text) {
        PdfPCell cell = new PdfPCell();
        cell.setPhrase(new Phrase(" " + text));
        cell.setMinimumHeight(25);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }
}
