package top.ytazwc.javacore;

/**
 * @author 花木凋零成兰
 * @title RegexValidDemo
 * @date 2024/5/27 14:30
 * @package top.ytazwc.javacore
 * @description TODO
 */
public class RegexValidDemo {
    static class User {
        private String name;
        @RegexValid(policy = RegexValid.Policy.DATE)
        private String date;
        @RegexValid(policy = RegexValid.Policy.MAIL)
        private String mail;
        @RegexValid("^(?:(?:\\+|00)86)?1(?:(?:3[\\d])|(?:4[5-79])|(?:5[0-35-9])|(?:6[5-7])|(?:7[0-8])|(?:8[\\d])|(?:9[189]))\\d{8}$")
        private String phone;

        public User(String name, String date, String mail, String phone) {
            this.name = name;
            this.date = date;
            this.mail = mail;
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", date='" + date + '\'' +
                    ", mail='" + mail + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        User user1 = new User("Tom", "1990-01-31", "xxx@163.com", "18612341234");
        User user2 = new User("Jack", "2019-02-29", "sadhgs", "183xxxxxxxx");
        if (RegexValidUtil.check(user1)) {
            System.out.println(user1 + "正则校验通过");
        }
        if (RegexValidUtil.check(user2)) {
            System.out.println(user2 + "正则校验通过");
        }
    }
}
