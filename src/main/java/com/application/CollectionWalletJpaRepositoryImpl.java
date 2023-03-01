package com.application;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CollectionWalletJpaRepositoryImpl implements CollectionWalletJpaRepository {

    private static Map<Integer,WalletJpaDto> WalletJpaDtoMap = new HashMap<>();


    @Override
    public WalletJpaDto createWallet(WalletJpaDto newWallet) {
        WalletJpaDtoMap.put(newWallet.getId(),newWallet);
        return newWallet;
    }



    @Override
    public WalletJpaDto getWalletById(Integer walletId) {
        return WalletJpaDtoMap.get(walletId);
    }

    @Override
    public WalletJpaDto updateWallet(WalletJpaDto wallet) {
        return WalletJpaDtoMap.replace(wallet.getId(),wallet);

    }

    @Override
    public WalletJpaDto deleteWallet(Integer walletId) {
        return WalletJpaDtoMap.remove(walletId);

    }

    @Override
    public List<WalletJpaDto> getAllWallets(){
        List<WalletJpaDto> walletList = new ArrayList<>();
        for(Map.Entry<Integer,WalletJpaDto> wallet: WalletJpaDtoMap.entrySet()){
            walletList.add(wallet.getValue());
        }
        return walletList;

    }


}
