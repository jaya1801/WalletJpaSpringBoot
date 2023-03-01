package com.application;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CollectionWalletJpaServiceImpl implements WalletJpaService{

    @Autowired
    private CollectionWalletJpaRepository collectionWalletJpaRepository;


    @Override
    public WalletJpaDto registerWallet(WalletJpaDto wallet){
        return collectionWalletJpaRepository.createWallet(wallet);

    }

    @Override
    public WalletJpaDto getWalletById(Integer walletId) throws WalletJpaException {
        WalletJpaDto foundWallet = collectionWalletJpaRepository.getWalletById(walletId);
        if(foundWallet == null)
            throw new WalletJpaException("Wallet Id does not exists.");
        return foundWallet;
    }


    @Override
    public WalletJpaDto updateWallet(WalletJpaDto wallet) throws WalletJpaException {
        return collectionWalletJpaRepository.updateWallet(wallet);


    }
    @Override
    public WalletJpaDto deleteWalletById(Integer walletId) throws WalletJpaException {
        WalletJpaDto foundWallet = collectionWalletJpaRepository.getWalletById(walletId);
        if(foundWallet==null)
            throw new WalletJpaException("Wallet couldn't be deleted, Id not found: "+walletId);
        return collectionWalletJpaRepository.deleteWallet(walletId);

    }

    @Override
    public Double addFundsToWalletById(Integer walletId, Double amount) throws WalletJpaException {
        WalletJpaDto wallet = getWalletById(walletId);
        Double balance = wallet.getBalance();
        wallet.setBalance(balance+amount);

        return balance+amount;

    }

    @Override
    public Double withdrawFundsFromWalletById(Integer walletById, Double amount) throws WalletJpaException {

        if(amount>getWalletById(walletById).getBalance())
            throw new WalletJpaException("Please enter amount lesser than your balance!");


        Double balance = getWalletById(walletById).getBalance() - amount;
        return balance;
    }

    @Override
    public Boolean fundTransfer(Integer fromWalletId, Integer toWalletId, Double amount) throws WalletJpaException {

        try{
            addFundsToWalletById(toWalletId,amount);
            withdrawFundsFromWalletById(fromWalletId,amount);

            return true;
        }
        catch (Exception e)
        {
            throw e;
        }

    }

    @Override
    public List<WalletJpaDto> getAllWallets() {
        return collectionWalletJpaRepository.getAllWallets();
    }
}
