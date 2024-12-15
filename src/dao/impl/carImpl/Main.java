package dao.impl.carImpl;

import dao.impl.clientImpl.*;
import entity.client.*;
import util.Path;

import java.time.LocalDateTime;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
//        CarModelServiceImpl carModelService = CarModelServiceImpl.getInstance();
//
//        List<CarModelDto> all = carModelService.findAll();
//        System.out.println(all);

        ClientDaoImpl clientDao = ClientDaoImpl.getInstance();
//        Client client = clientDao.create(Client.builder()
//                .fullName("SERG")
//                .phoneNumber(234)
//                .email("wdfsd")
//                .residentialAddress("sdfsv")
//                .role(RoleDaoImpl.getInstance().create(Role.builder().role("USER").build()))
//                .passport(PassportDaoImpl.getInstance().create(Passport.builder().serialNumber("324").expiredData(LocalDateTime.now()).build()))
//                .driverLicense(DriverLicenseDaoImpl.getINSTANCE().create(DriverLicense.builder().serialNumber("324").expiredData(LocalDateTime.now()).build()))
//                .bankCard(BankCardDaoImpl.getInstance().create(BankCard.builder().serialNumber("324").expiredData(LocalDateTime.now()).build()))
//                .build());
//        System.out.println(client);
//        Long user = clientDao.findByNameRole("USER");
//        System.out.println(user);
//        Optional<Client> sdv = clientDao.findByEmailAndPassword("sdv", "333");
//        System.out.println(sdv);
    }

}