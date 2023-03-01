package com.application;

import java.util.List;
import java.util.Optional;

public interface WalletJpaService {

    WalletJpaDto registerWallet(WalletJpaDto wallet);
    WalletJpaDto getWalletById(Integer walletId) throws WalletJpaException;
    WalletJpaDto updateWallet(WalletJpaDto wallet)throws WalletJpaException;
    WalletJpaDto deleteWalletById(Integer walletId)throws WalletJpaException;


    Double addFundsToWalletById(Integer walletId,Double amount)throws WalletJpaException;
    Double withdrawFundsFromWalletById(Integer walletById,Double amount) throws WalletJpaException;
    Boolean fundTransfer(Integer fromWalletId,Integer toWalletId,Double amount)throws WalletJpaException;

    List<WalletJpaDto> getAllWallets();


}
