package com.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletJpaServiceImpl implements WalletJpaService {

    @Autowired
    private WalletJpaRepository WalletJpaRepository;

    @Override
    public WalletJpaDto registerWallet(WalletJpaDto wallet){
        return WalletJpaRepository.save(wallet);

    }

    @Override
    public WalletJpaDto getWalletById(Integer walletId) throws WalletJpaException {
        Optional<WalletJpaDto> walletOptional = WalletJpaRepository.findById(walletId);
        if(walletOptional.isEmpty())
            throw new WalletJpaException("Wallet Id does not exists.");
        return walletOptional.get();
    }


    @Override
    public WalletJpaDto updateWallet(WalletJpaDto wallet) throws WalletJpaException {
        Optional<WalletJpaDto> walletOptional = this.WalletJpaRepository.findById(wallet.getId());
        if(walletOptional.isEmpty())
            throw new WalletJpaException("Wallet can not be updated!Id not found:"+wallet.getId());
        return this.WalletJpaRepository.save(wallet);


    }
    @Override
    public WalletJpaDto deleteWalletById(Integer walletId) throws WalletJpaException {
        Optional<WalletJpaDto> walletOptional = WalletJpaRepository.findById(walletId);
        if(walletOptional.isEmpty())
            throw new WalletJpaException("Wallet couldn't be deleted, Id not found: "+walletId);
        WalletJpaDto foundWallet = walletOptional.get();
        this.WalletJpaRepository.delete(foundWallet);
        return foundWallet;
    }

    @Override
    public Double addFundsToWalletById(Integer walletId, Double amount) throws WalletJpaException {
        //WalletJpaDto wallet = getWalletById(walletId);
        Optional<WalletJpaDto> walletOptional = WalletJpaRepository.findById(walletId);
        if(walletOptional.isEmpty())
            throw new WalletJpaException("Funds con not be added to wallet!Id not found: "+walletId);
        Double balance = walletOptional.get().getBalance();
        //walletOptional.setBalance(balance+amount);
        walletOptional.equals(balance+amount);
        return balance+amount;

    }

    @Override
    public Double withdrawFundsFromWalletById(Integer walletById, Double amount) throws WalletJpaException {

//        if(amount>getWalletById(walletById).getBalance())
//            throw new WalletJpaException("Please enter amount lesser than your balance!");
        Optional<WalletJpaDto> walletOptional = WalletJpaRepository.findById(walletById);
        if(amount>walletOptional.get().getBalance())
            throw new WalletJpaException("Please enter amount lesser than your balance! Your current balance is: "+walletOptional);


//        Double balance = getWalletById(walletById).getBalance() - amount;
//        return balance;
        Double balance = walletOptional.get().getBalance()-amount;
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
        return  this.WalletJpaRepository.findAll();

    }






}
