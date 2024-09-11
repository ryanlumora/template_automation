package br.com.automation.configs;

import lombok.extern.log4j.Log4j2;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;

import static org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName.HELVETICA;

@Log4j2
public class PDFGenerator{

    private static PDFGenerator instance;

    private static final List<BufferedImage> screenshots = new ArrayList<>();
    private static final List<String> captions = new ArrayList<>();

    private PDFGenerator(){}

    public static PDFGenerator pdfGenerator(){
        if (instance == null)
            instance = new PDFGenerator();

        return instance;
    }

    public void takeScreenshot(WebDriver driver, String step) {
        byte[] screenshotData = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        try (ByteArrayInputStream bis = new ByteArrayInputStream(screenshotData)) {
            BufferedImage screenshot = ImageIO.read(bis);
            screenshots.add(screenshot);
            captions.add(step);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar captura de tela", e);
        }
    }

    public void createPDF() {
        log.info("Gerando PDF...");

        String dataHoraAtual = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String scenarioName = Hooks.getScenarioName().replace(" ", "_");
        String projectDir = System.getProperty("user.dir");
        String pdfDir = projectDir + "/src/main/resources/evidences";
        String nomeArquivoPdf = String.format("%s/%s_%s.pdf", pdfDir, scenarioName, dataHoraAtual);

        // Cria o diretório se não existir
        new File(pdfDir).mkdirs();

        try {
            createPdfWithImages(nomeArquivoPdf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createPdfWithImages(String pdfFileName) throws IOException {
        try (PDDocument document = new PDDocument()) {
            for (int i = 0; i < screenshots.size(); i++) {
                BufferedImage bufferedImage = screenshots.get(i);
                String caption = captions.get(i);

                PDPage page = new PDPage();
                document.addPage(page);

                try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true)) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(bufferedImage, "PNG", baos);
                    baos.flush();
                    byte[] imageBytes = baos.toByteArray();
                    ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
                    PDImageXObject pdfImage = PDImageXObject.createFromByteArray(document, bais.readAllBytes(), "Screenshot");

                    float imageWidth = pdfImage.getWidth();
                    float imageHeight = pdfImage.getHeight();
                    float pageWidth = page.getMediaBox().getWidth();
                    float pageHeight = page.getMediaBox().getHeight();

                    float scale = Math.min(pageWidth / imageWidth, (pageHeight - 50) / imageHeight);
                    float scaledWidth = imageWidth * scale;
                    float scaledHeight = imageHeight * scale;

                    float xPosition = (pageWidth - scaledWidth) / 2;
                    float yPosition = pageHeight - scaledHeight - 60;

                    contentStream.drawImage(pdfImage, xPosition, yPosition, scaledWidth, scaledHeight);

                    contentStream.beginText();
                    contentStream.setFont(new PDType1Font(HELVETICA), 20);
                    contentStream.newLineAtOffset(100, yPosition - 30);
                    contentStream.showText(caption);
                    contentStream.endText();
                }
            }


            document.save(pdfFileName);
        }
    }
}