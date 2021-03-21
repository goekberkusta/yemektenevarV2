package cmpe494.yemektenevar.Pisiren.Model;

public class Pisiren {

    private String name,pw,tel;

    public Pisiren() {

    }
    public Pisiren(String s, String s2, String s3)
    {
        name = s;
        pw = s2;
        tel = s3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
