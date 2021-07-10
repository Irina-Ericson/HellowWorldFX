package sample;


import javafx.scene.control.Button;

public class Book {
    private String fNamn;
    private String eNamn;
    private String titel;
    private int idArtikelBarcode;

    Book(String fNamn, String eNamn, String titel, int idArtikelBarcode) {
        this.fNamn = fNamn;
        this.eNamn = eNamn;
        this.titel = titel;
        this.idArtikelBarcode=idArtikelBarcode;

    }

    public String getFNamn() {
        return fNamn;
    }

    public void setFNamn(String fNamn) {
        this.fNamn = fNamn;
    }

    public String getENamn() {
        return eNamn;
    }

    public void setENamn(String eNamn) {
        this.eNamn = eNamn;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public int getIdArtikelBarcode() {
        return idArtikelBarcode;
    }

    public void setIdArtikelBarcode(int idArtikelBarcode) {
        this.idArtikelBarcode = idArtikelBarcode;
    }

    public Book(){

}}
