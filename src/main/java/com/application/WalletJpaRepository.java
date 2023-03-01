package com.application;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface WalletJpaRepository extends JpaRepository<WalletJpaDto,Integer> {

    List<WalletJpaDto> findByName(String name);
    List<WalletJpaDto> findByNameContaining(String name);
    List<WalletJpaDto> findByBalanceBetweenOrderByNameDesc(Double minBalance,Double maxBalance);
    List<WalletJpaDto> findByBalanceBetweenOrderByNameAsc(Double minBalance,Double maxBalance);
    List<WalletJpaDto> findByBalanceBetweenOrderByBalanceDesc(Double minBalance,Double maxBalance);
    List<WalletJpaDto> findByBalanceGreaterThanEqual(Double minBalance);
    List<WalletJpaDto> findByIdOrName(Integer id,String name);
    List<WalletJpaDto> findByNameStartingWith(String name);
    List<WalletJpaDto> findByNameEndingWith(String name);
    List<WalletJpaDto> findByNameIsNot(String name);
    // By writing JPQL Queries
    @Query("SELECT wallet FROM WalletJpaDto wallet")
    List<WalletJpaDto> getAllWallets();

    @Query("SELECT wallet FROM WalletJpaDto wallet WHERE wallet.name LIKE :name")
    List<WalletJpaDto> getAllWalletsHavingNameLike(String name);

    @Query("SELECT wallet FROM WalletJpaDto wallet ORDER BY wallet.name")
    List<WalletJpaDto> getAllWalletsHavingOrderByName(String name);

    @Query("SELECT wallet FROM WalletJpaDto wallet ORDER BY wallet.balance")
    List<WalletJpaDto> getAllWalletsHavingBalanceOrderByBalance(Double balance);

    @Query("SELECT wallet FROM WalletJpaDto wallet WHERE wallet.id > id")
    List<WalletJpaDto> getAllWalletsHavingIdGreaterThan(Integer id);








}
