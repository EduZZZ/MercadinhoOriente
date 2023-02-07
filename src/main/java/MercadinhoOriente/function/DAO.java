package MercadinhoOriente.function;

import MercadinhoOriente.main.Cashier;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class DAO {

    private static final String fileName = "C:\\Users\\Eduardo\\Documents\\MercadoOriente\\spreadsheet.xls";

    public void readDaywithTurn(int day, int month, boolean turn) {

        try {

            InputStream file = getClass().getResourceAsStream(fileName);
            
            FileInputStream arquivo = new FileInputStream(fileName);
                                    

            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheetCashier = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheetCashier.iterator();

            if (turn == true) {
                day = (day * 2) - 2;
            } else {
                day = (day * 2) - 1;
            }

            System.out.println("Ferias - "+workbook.getSheetAt(0).getRow(day).getCell((month * 3) - 1).getNumericCellValue());
            System.out.println("Vendas de cartao - "+workbook.getSheetAt(0).getRow(day).getCell((month * 3)).getNumericCellValue());
            System.out.println("Despesa - "+workbook.getSheetAt(0).getRow(day).getCell((month * 3) + 1).getNumericCellValue());

            file.close();

            FileOutputStream outFile = new FileOutputStream(fileName);
            workbook.write(outFile);
            outFile.close();
            System.out.println("Arquivo Excel editado com sucesso!");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Arquivo Excel não encontrado!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro na edição do arquivo!");
        }

    }

  public List<Cashier> cashierList(int month) throws IOException {

        List<Cashier> cashierList = new ArrayList<>();
        

        try {

            FileInputStream arquivo = new FileInputStream(fileName);

            HSSFWorkbook workbook = new HSSFWorkbook(arquivo);

            HSSFSheet sheetCashier = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheetCashier.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                Cashier cashier = new Cashier();
               
                cashierList.add(cashier);
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    if (cell.getColumnIndex() == 0) {
                        cashier.setTurn(cell.getStringCellValue());
                    }
                    if (cell.getColumnIndex() == 1) {
                        cashier.setDay((int) cell.getNumericCellValue());
                    }

                    cashier.setMonth(month);

                    if (cell.getColumnIndex() == (month * 3) - 1) {
                        cashier.setBilling(cell.getNumericCellValue());
                    }
                    if (cell.getColumnIndex() == (month * 3)) {
                        cashier.setSaleCard(cell.getNumericCellValue());
                    }
                    if (cell.getColumnIndex() == (month * 3) + 1) {
                        cashier.setExpense(cell.getNumericCellValue());
                    }
                }
            }
            arquivo.close();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
            System.out.println("Arquivo Excel não encontrado!");
        }

        if (cashierList.size() == 0) {
            System.out.println("Nenhum registro encontrado!");
        } else {
            
            for (Cashier cashier : cashierList) {
                System.out.println(cashier.getTurn() + " - " + cashier.getDay() + " - " + cashier.getBilling() + " - " + cashier.getSaleCard() + " - " + cashier.getExpense());
            }
            
        }
        return cashierList;
      
    }


    public void edit(int day, int month, boolean turn, int item, double edit) throws IOException {

        try {

            FileInputStream file = new FileInputStream(fileName);

            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheetCashier = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheetCashier.iterator();

            if (turn == true) {
                day = (day * 2) - 2;
            } else {
                day = (day * 2) - 1;
            }

            if (item == 1) {
                month = (month * 3) - 1; //billing
            } else if (item == 2) {
                month = month * 3; //saleCard
            } else if (item == 3) {
                month = (month * 3) + 1; //expense
            } else {
                System.out.println("Inválido"); //Invalid index
            }

            workbook.getSheetAt(0).getRow(day).getCell(month).setCellValue(edit);

            file.close();

            FileOutputStream outFile = new FileOutputStream(fileName);
            workbook.write(outFile);
            outFile.close();
            System.out.println("Arquivo Excel editado com sucesso!");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Arquivo Excel não encontrado!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro na edição do arquivo!");
        }

    }

    public void closing(int day, int month, boolean turn, double billing, double saleCard, double expense) throws IOException {

        try {

            FileInputStream file = new FileInputStream(fileName);

            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheetCashier = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheetCashier.iterator();

            if (turn == true) {
                day = (day * 2) - 2;
            } else {
                day = (day * 2) - 1;
            }

            workbook.getSheetAt(0).getRow(day).getCell((month * 3) - 1).setCellValue(billing);
            workbook.getSheetAt(0).getRow(day).getCell((month * 3)).setCellValue(saleCard);
            workbook.getSheetAt(0).getRow(day).getCell((month * 3) + 1).setCellValue(expense);

            file.close();

            FileOutputStream outFile = new FileOutputStream(fileName);
            workbook.write(outFile);
            outFile.close();
            System.out.println("Arquivo Excel editado com sucesso!");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Arquivo Excel não encontrado!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro na edição do arquivo!");
        }

    }

}
