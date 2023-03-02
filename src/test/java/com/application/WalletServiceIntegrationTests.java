package com.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class WalletServiceIntegrationTests {

    @Autowired
    private WalletJpaService walletService;

    @Test
    void registerWalletTest() throws WalletJpaException {
        WalletJpaDto wallt = new WalletJpaDto(3,"wallet1",1000.0);
        assertEquals(wallt.toString(),this.walletService.registerWallet(wallt).toString());
    }

    @Test
    void getWalletByIdThrowsExceptionTest() throws WalletJpaException {
        assertThrows(WalletJpaException.class,()->this.walletService.getWalletById(1000));
    }

    @Test
    void getWalletByIdWithoutExceptionTest() throws WalletJpaException {
        WalletJpaDto wallt = new WalletJpaDto(1,"wallet1",1000.0);
        this.walletService.registerWallet(wallt);
        assertEquals(wallt.toString(),this.walletService.getWalletById(wallt.getId()).toString());
    }

    @Test
    void updateWalletTest() throws WalletJpaException {
        WalletJpaDto wallt = new WalletJpaDto(1,"wallet1",1000.0);
        this.walletService.registerWallet(wallt);
        assertEquals(wallt.toString(),this.walletService.updateWallet(wallt).toString());
    }

    @Test
    void deleteWalletByIdThrowsExceptionTest() throws WalletJpaException {
        assertThrows(WalletJpaException.class,()->this.walletService.deleteWalletById(1000));
    }

    @Test
    void deleteWalletByIdWithoutExceptionTest() throws WalletJpaException {
        WalletJpaDto wallt = new WalletJpaDto(1,"wallet1",1000.0);
        this.walletService.registerWallet(wallt);
        assertEquals(wallt.toString(),this.walletService.deleteWalletById(wallt.getId()).toString());
    }

    @Test
    void addFundsToWalletByIdWithoutExceptionTest() throws WalletJpaException{
        WalletJpaDto wallt = new WalletJpaDto(1,"wallet1",1000.0);
        this.walletService.registerWallet(wallt);

        assertEquals(1500.0,this.walletService.addFundsToWalletById(1, 500.0));
    }

    @Test
    void addFundsToWalletByIdThrowsExceptionTest() throws WalletJpaException{
        assertThrows(WalletJpaException.class,()->this.walletService.addFundsToWalletById(1000, 500.0));
    }

    @Test
    void withdrawFundsFromWalletByIdThrowsExceptionTest() throws WalletJpaException, Exception {
        assertThrows(Exception.class,()->this.walletService.withdrawFundsFromWalletById(1000, 500.0));
    }

    @Test
    void withdrawFundsFromWalletByIdLessAmountTest() throws WalletJpaException {
        WalletJpaDto wallt = new WalletJpaDto(1,"wallet1",10.0);
        this.walletService.registerWallet(wallt);

        assertThrows(WalletJpaException.class,()->this.walletService.withdrawFundsFromWalletById(1, 500.0));
    }

    @Test
    void withdrawFundsFromWalletByIdWithoutExceptionTest() throws WalletJpaException{
        WalletJpaDto wallt = new WalletJpaDto(1,"wallet1",1000.0);
        this.walletService.registerWallet(wallt);

        assertEquals(500.0,this.walletService.withdrawFundsFromWalletById(1, 500.0));
    }

    @Test
    void fundTransferThrowsExceptionFromIdTest() throws WalletJpaException, Exception {
        assertThrows(Exception.class,()->this.walletService.fundTransfer(1000,2,1000.0));
    }

    @Test
    void fundTransferThrowsExceptionToIdTest() throws WalletJpaException, Exception {
        assertThrows(Exception.class,()->this.walletService.fundTransfer(1,2000,1000.0));
    }

    @Test
    void fundTransferThrowsExceptionLessBalanceTest() throws WalletJpaException{
        WalletJpaDto wallt1 = new WalletJpaDto(1,"wallet1",10.0);
        WalletJpaDto wallt2 = new WalletJpaDto(2,"wallet2",500.0);
        this.walletService.registerWallet(wallt1);
        this.walletService.registerWallet(wallt2);

        assertThrows(WalletJpaException.class,()->this.walletService.fundTransfer(1,2,1000.0));
    }

    @Test
    void fundTransferWithoutExceptionTest() throws WalletJpaException{
        WalletJpaDto wallt1 = new WalletJpaDto(1,"wallet1",1000.0);
        WalletJpaDto wallt2 = new WalletJpaDto(2,"wallet2",500.0);
        this.walletService.registerWallet(wallt1);
        this.walletService.registerWallet(wallt2);

        assertEquals(true,this.walletService.fundTransfer(1, 2,500.0));
    }
}
