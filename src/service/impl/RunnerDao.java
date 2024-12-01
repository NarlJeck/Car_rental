package service.impl;

import dao.clientDao.PassportDao;
import dao.clientDao.RoleDao;
import dao.impl.clientImpl.*;
import entity.client.*;
import java.time.LocalDateTime;

public class RunnerDao {

    public static void main(String[] args) {

        ClientDaoImpl clientDao = ClientDaoImpl.getInstance();

        RoleDao roleDao = RoleDaoImpl.getInstance();
        Role role = roleDao.create(Role.builder()
                .role("User")
                .build());

        PassportDao passportDao = PassportDaoImpl.getInstance();
        Passport passport = passportDao.create(Passport.builder()
                .serialNumber("35235232")
                .expiredData(LocalDateTime.now())
                .build());

        DriverLicenseDaoImpl driverLicenseDao = DriverLicenseDaoImpl.getINSTANCE();
        DriverLicense driverLicense = driverLicenseDao.create(DriverLicense.builder()
                .serialNumber("35235232")
                .expiredData(LocalDateTime.now())
                .build());
        BankCardDaoImpl bankCardDao = BankCardDaoImpl.getInstance();
        BankCard bankCard = bankCardDao.create(BankCard.builder()
                .serialNumber("35235232")
                .expiredData(LocalDateTime.now())
                .build());

        clientDao.create(Client.builder()
                .fullName("Nick Perumow")
                .phoneNumber(299803423)
                .email("Nattt@gmail.com")
                .residentialAddress("Tokio, st. Lotos 34 k.23")
                .role(role)
                .passport(passport)
                .driverLicense(driverLicense)
                .bankCard(bankCard)
                .build());

        System.out.println(clientDao.findById(2l));
        System.out.println(clientDao.findAll());
        System.out.println(clientDao.delete(6l));
    }


}
