package application.viewmodel.common;

public class LogoVM {
    private String companyName;
    private String logoUrl;
    private String logoAltText;
    private String companyBrand;

    public LogoVM (String companyName, String logoUrl, String logoAltText, String companyBrand) {
        this.companyName = companyName;
        this.logoAltText = logoAltText;
        this.logoUrl = logoUrl;
        this.companyBrand = companyBrand;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getLogoAltText() {
        return logoAltText;
    }

    public void setLogoAltText(String logoAltText) {
        this.logoAltText = logoAltText;
    }

    public String getCompanyBrand() {
        return companyBrand;
    }

    public void setCompanyBrand(String companyBrand) {
        this.companyBrand = companyBrand;
    }
}
