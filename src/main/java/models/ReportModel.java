package models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

public class ReportModel {

    private String title;
    private List<TransactionModel> transactions;
    private String date;

    public ReportModel(Date date, List<TransactionModel> transactions) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.title = "Reporte del día: "+ formatter.format(date);
        this.transactions = transactions;
        this.date = formatter.format(date);
    }

    public String getTitle() {
        return title;
    }

    public List<TransactionModel> getTransactions() {
        return transactions;
    }

    public String getDate(){
        return date;
    }

}
