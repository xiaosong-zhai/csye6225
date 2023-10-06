package northeastern.xiaosongzhai.csye6225;

import northeastern.xiaosongzhai.csye6225.entity.Account;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//@SpringBootTest
class Csye6225ApplicationTests {

    @Test
    void loadCSVFile() {
//        try {Resource resource = new ClassPathResource("static/users.csv");
//            CSVParser csvParser = new CSVParser(new InputStreamReader(resource.getInputStream()), CSVFormat.DEFAULT.withHeader());
//            List<Account> accounts = new ArrayList<>();
//            for (CSVRecord csvRecord : csvParser) {
//                String first_name = csvRecord.get("first_name");
//                String last_name = csvRecord.get("last_name");
//                String password = csvRecord.get("password");
//                String email = csvRecord.get("email");
//                Account account = new Account();
//                account.setFirst_name(first_name);
//                account.setLast_name(last_name);
//                account.setPassword(password);
//                account.setEmail(email);
//                accounts.add(account);
//            }
//            System.out.println(accounts);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

}
