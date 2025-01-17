//package com.aurionpro.model.service;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.util.List;
//
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.PDPageContentStream;
//import org.apache.pdfbox.pdmodel.font.PDType1Font;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.aurionpro.model.entity.BankAccount;
//import com.aurionpro.model.entity.Transaction;
//import com.aurionpro.model.respository.TransactionRepository;
//
//@Service
//public class PdfService {
//
//    @Autowired
//    private TransactionRepository transactionRepository;
//
//    public byte[] generateTransactionPdf(BankAccount account) throws IOException {
//        List<Transaction> transactions = transactionRepository.findBySenderAccountOrReceiverAccount(account, account);
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        try (PDDocument document = new PDDocument()) {
//            PDPage page = new PDPage();
//            document.addPage(page);
//            PDPageContentStream contentStream = new PDPageContentStream(document, page);
//            
//            float yPosition = page.getMediaBox().getHeight() - 50;
//            float margin = 50;
//            float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
//            float tableHeight = 0f;
//
//            contentStream.beginText();
//            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
//            contentStream.newLineAtOffset(margin, yPosition);
//            contentStream.showText("Transaction Report");
//            contentStream.endText();
//
//            yPosition -= 30;
//
//            // Draw table
//            contentStream.setLineWidth(1f);
//            contentStream.moveTo(margin, yPosition);
//            contentStream.lineTo(margin + tableWidth, yPosition);
//            contentStream.stroke();
//            yPosition -= 10;
//
//            contentStream.setFont(PDType1Font.HELVETICA, 10);
//            contentStream.beginText();
//            contentStream.newLineAtOffset(margin, yPosition);
//            contentStream.showText("Date");
//            contentStream.newLineAtOffset(100, 0);
//            contentStream.showText("Amount");
//            contentStream.newLineAtOffset(100, 0);
//            contentStream.showText("Type");
//            contentStream.newLineAtOffset(100, 0);
//            contentStream.showText("Sender");
//            contentStream.newLineAtOffset(100, 0);
//            contentStream.showText("Receiver");
//            contentStream.endText();
//
//            yPosition -= 20;
//            for (Transaction transaction : transactions) {
//                contentStream.beginText();
//                contentStream.newLineAtOffset(margin, yPosition);
//                contentStream.showText(transaction.getDate().toString());
//                contentStream.newLineAtOffset(100, 0);
//                contentStream.showText(transaction.getAmount().toString());
//                contentStream.newLineAtOffset(100, 0);
//                contentStream.showTextInternal(transaction.getTransactionType());
//                contentStream.newLineAtOffset(100, 0);
//                contentStream.showText(transaction.getSenderAccount().getAccountNumber());
//                contentStream.newLineAtOffset(100, 0);
//                contentStream.showText(transaction.getReceiverAccount().getAccountNumber());
//                contentStream.endText();
//
//                yPosition -= 20;
//            }
//
//            // Draw total row
//            contentStream.setLineWidth(1f);
//            contentStream.moveTo(margin, yPosition);
//            contentStream.lineTo(margin + tableWidth, yPosition);
//            contentStream.stroke();
//            yPosition -= 20;
//
//            BigDecimal totalAmount = transactions.stream()
//                .map(Transaction::getAmount)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//            contentStream.beginText();
//            contentStream.newLineAtOffset(margin, yPosition);
//            contentStream.showText("Total");
//            contentStream.newLineAtOffset(100, 0);
//            contentStream.showText(totalAmount.toString());
//            contentStream.endText();
//
//            contentStream.close();
//            document.save(outputStream);
//        }
//        return outputStream.toByteArray();
//    }
//}
