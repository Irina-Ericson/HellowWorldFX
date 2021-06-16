package sample;

public class User_Controller {
    private int ID;
    private String name;
    private String surname;
    private char telefonnummer;
    private char password;
    private int postnummer;
    private String street;
    private String city;
    private String email;
    private int ID_låntagar_kat;
    private int lånekortsnummer;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public char getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(char telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public char getPassword() {
        return password;
    }

    public void setPassword(char password) {
        this.password = password;
    }

    public int getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(int postnummer) {
        this.postnummer = postnummer;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getID_låntagar_kat() {
        return ID_låntagar_kat;
    }

    public void setID_låntagar_kat(int ID_låntagar_kat) {
        this.ID_låntagar_kat = ID_låntagar_kat;
    }

    public int getLånekortsnummer() {
        return lånekortsnummer;
    }

    public void setLånekortsnummer(int lånekortsnummer) {
        this.lånekortsnummer = lånekortsnummer;
    }
}
