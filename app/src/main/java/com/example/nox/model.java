package com.example.nox;

public class model {
    String id , CardName , CNumber , Expdate , CVV;
    public model(String id, String cardName, String cNumber){}


    public model(String id , String CardName , String CNumber , String Expdate , String CVV){
        this.id=id;
        this.CardName=CardName;
        this.CNumber=CNumber;
        this.Expdate=Expdate;
        this.CVV=CVV;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardName() {
        return CardName;
    }

    public void setCardName(String cardName) {
        CardName = cardName;
    }

    public String getCNumber() {
        return CNumber;
    }

    public void setCNumber(String CNumber) {
        this.CNumber = CNumber;
    }

    public String getExpdate() {
        return Expdate;
    }

    public void setExpdate(String expdate) {
        Expdate = expdate;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }
}
