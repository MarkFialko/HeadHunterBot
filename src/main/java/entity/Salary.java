package entity;

public  class Salary {
    public Integer from;
    public Integer to;
    public String currency;
    public boolean gross;

    public String getCurrency() {
        return currency;
    }

    public Integer getFrom() {
        return from;
    }

    public Integer getTo() {
        return to;
    }

    public boolean getGross() {
        return gross;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public void setGross(boolean gross) {
        this.gross = gross;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }
}