package models;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.DataFormatException;

public class TransactionModel {

    private Integer id;

    private Date createdDate;

    private Integer clientId;

    private String product;

    private Double amount;

    private Double miles;

    private Double  exchangeRate;

    public TransactionModel(Integer id, Date createdDate, Integer clientId, String product, Double amount, Double exchangeRate) {
        try{
            if(product.length() <= 50){
                this.id = id;
                this.createdDate = createdDate;
                this.clientId = clientId;
                this.product = product;
                this.amount = amount;
                this.miles = (double)Math.round(amount/exchangeRate);
                this.exchangeRate = exchangeRate;
            }else{
                throw new DataFormatException("Ha sobrepasado el limite de 50 caracteres para el nombre del producto");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            this.id = -1;
        }
    }

    public Integer getId() {
        return id;
    }

    public Double getMiles(){
        return miles;
    }

    public Date getCreatedDate(){
        return createdDate;
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = formatter.format(createdDate);
//        String.format("%.2f", amount)
        return id +"|" + date +"|" + clientId +"|" + product +"|" + "$" + decimalFormat.format(amount) +"|" + "Millas "+ decimalFormat.format(miles) +"|" + exchangeRate ;
    }

}
