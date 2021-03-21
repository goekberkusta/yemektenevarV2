package cmpe494.yemektenevar.Yiyen.Model;

public class Yiyen {

    private String name,password,telephone;

    public Yiyen() {

    }
    public Yiyen(String s, String s2, String s3)
    {
        name = s;
        password = s2;
        telephone = s3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pw) {
        this.password = pw;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String tel) {
        this.telephone = tel;
    }
}

