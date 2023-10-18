package northeastern.xiaosongzhai.csye6225.config;

import northeastern.xiaosongzhai.csye6225.entity.Account;
import northeastern.xiaosongzhai.csye6225.repository.AccountRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: Xiaosong Zhai
 * @date: 2023/10/1 19:31
 * @Description: init user table if it has no user
 */
@Component
public class InitDatabase implements CommandLineRunner {
    private static final String JOHN_EMAIL = "john.doe@example.com";
    private static final String JANE_EMAIL = "jane.doe@example.com";

    @Autowired
    private AccountRepository accountRepository;
    @Value("${csv.file.path}")
    private String csvFilePath;

    @Override
    public void run(String... args) throws Exception {
        // check if database has basic user john and jane
        boolean existJohn = accountRepository.existsByEmail(JOHN_EMAIL);
        boolean existJane = accountRepository.existsByEmail(JANE_EMAIL);

        // if not, init database
        if (!existJohn && !existJane) {
            saveALlUser();
        } else if(!existJohn){
            saveUser(0);
        } else if(!existJane){
            saveUser(1);
        }
    }

    private List<Account> loadCSVFile() {
        List<Account> accounts = null;
        try {Resource resource = new DefaultResourceLoader().getResource(csvFilePath);
            CSVParser csvParser = new CSVParser(new InputStreamReader(resource.getInputStream()), CSVFormat.DEFAULT.withHeader());
            accounts = new ArrayList<>();
            for (CSVRecord csvRecord : csvParser) {
                String id = UUID.randomUUID().toString();
                String firstName = csvRecord.get("first_name");
                String lastName = csvRecord.get("last_name");
                String password = new BCryptPasswordEncoder().encode(csvRecord.get("password"));
                String email = csvRecord.get("email");
                String accountCreated = LocalDateTime.now().toString();
                String accountUpdated = LocalDateTime.now().toString();
                Account account = Account.builder()
                        .id(id)
                        .first_name(firstName)
                        .last_name(lastName)
                        .password(password)
                        .email(email)
                        .account_created(accountCreated)
                        .account_updated(accountUpdated)
                        .build();
                accounts.add(account);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    // save all users to database
    private void saveALlUser() {
        List<Account> accounts = loadCSVFile();
        accountRepository.saveAll(accounts);
    }
    // save one user to database
    private void saveUser(int index) {
        List<Account> accounts = loadCSVFile();
        accountRepository.save(accounts.get(index));
    }
}
