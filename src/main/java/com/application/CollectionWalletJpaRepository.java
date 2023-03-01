package com.application;

import java.util.List;

public interface CollectionWalletJpaRepository {

    WalletJpaDto createWallet(WalletJpaDto newWallet);
    WalletJpaDto getWalletById(Integer  walletId);
    WalletJpaDto updateWallet(WalletJpaDto wallet);
    WalletJpaDto deleteWallet(Integer walletId);

    List<WalletJpaDto> getAllWallets();

}
