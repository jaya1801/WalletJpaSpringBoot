package com.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//localhost:8093/swagger-ui.html
@RestController
@RequestMapping(value = "/v1") //
public class WalletJpaController {

    @Autowired
    private WalletJpaService walletJpaService;

    @GetMapping("/")
    public String greet(){
        return "This is my Wallet Application!";
    }



    @PostMapping("/wallet")
    @ResponseStatus(value = HttpStatus.CREATED)
    public WalletJpaDto addResource(@RequestBody WalletJpaDto wallet) throws WalletJpaException{
        return walletJpaService.registerWallet(wallet);

    }

    @GetMapping("/wallet/{id}")
    public WalletJpaDto getWalletById(@PathVariable Integer id) throws WalletJpaException{
        Optional<WalletJpaDto> walletOptional = walletJpaRepository.findById(id);
        if(walletOptional.isEmpty())
            throw new WalletJpaException("Wallet not found!");
        return walletOptional.get();
    }

    @PutMapping("/wallet")
    public WalletJpaDto replaceResource(@RequestBody WalletJpaDto wallet) throws WalletJpaException
    {
        return walletJpaService.updateWallet(wallet);
    }

    @PatchMapping("/wallet/{walletId}/addFund/{amount}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    Double addFundsToWalletById(@PathVariable Integer walletId, @PathVariable Double amount) throws WalletJpaException {
        return walletJpaService.addFundsToWalletById(walletId, amount);
    }

    @PatchMapping("/wallet/{walletId}/withdrawFund/{amount}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    Double withdrawFundsFromWalletById(@PathVariable Integer walletId, @PathVariable Double amount) throws WalletJpaException {
        return walletJpaService.withdrawFundsFromWalletById(walletId, amount);
    }

    @PatchMapping("/wallet/{fromWalletId}/{toWalletId}/fundTransfer/{amount}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    Boolean fundTransfer(@PathVariable Integer fromWalletId, @PathVariable Integer toWalletId, @PathVariable Double amount) throws WalletJpaException {
        return walletJpaService.fundTransfer(fromWalletId, toWalletId, amount);
    }

    @DeleteMapping("/wallet/{walletId}")
    public WalletJpaDto deleteWalletById(@PathVariable Integer walletId) throws WalletJpaException
    {
        return walletJpaService.deleteWalletById(walletId);
    }


    @Autowired
    private WalletJpaRepository walletJpaRepository;

    @GetMapping("/wallet/name/{name}")
    public List<WalletJpaDto> getAllWalletsHavingName(@PathVariable("name") String name){
        return this.walletJpaRepository.findByName(name);
    }

    @GetMapping("/wallet/contain/{name}")
    public List<WalletJpaDto> getAllNamesContaining(@PathVariable("name") String name){
        return this.walletJpaRepository.findByNameContaining(name);
    }

    @GetMapping("/wallet/balance/{minBalance}/{maxBalance}/name/{name}/nameDesc ")
    public List<WalletJpaDto> getAllWalletsHavingBalanceOrderByNameDesc(@PathVariable("minBalance") Double minBalance,@PathVariable("maxBalance") Double maxBalance){
        return this.walletJpaRepository.findByBalanceBetweenOrderByNameDesc(minBalance,maxBalance);
    }

    @GetMapping("/wallet/balance/{minBalance}/{maxBalance}/name/{name}/nameAsc")
    public List<WalletJpaDto> getAllWalletsHavingBalanceBetweenOrderByNameAsc(@PathVariable("minBalance") Double minBalance,@PathVariable("maxBalance") Double maxBalance){
        return this.walletJpaRepository.findByBalanceBetweenOrderByNameAsc(minBalance,maxBalance);
    }

    @GetMapping("/wallet/balance/balanceDesc/{minBalance}/{maxBalance}")
    public List<WalletJpaDto> getAllWalletsHavingBalanceBetweenOrderByBalanceDesc(@PathVariable("minBalance") Double minBalance,@PathVariable("maxBalance") Double maxBalance){
        return this.walletJpaRepository.findByBalanceBetweenOrderByBalanceDesc(minBalance,maxBalance);
    }

    @GetMapping("/wallet/balance/{minBalance}/greaterThanBalance")
    public List<WalletJpaDto> getAllWalletsHavingBalanceGreaterThanEqual(@PathVariable("minBalance") Double minBalance){
        return this.walletJpaRepository.findByBalanceGreaterThanEqual(minBalance);
    }

    @GetMapping("/wallet/id/{id}/name/{name}")
    public List<WalletJpaDto> getAllWalletsHavingIdOrName(@PathVariable("id")Integer id,@PathVariable("name")String name){
        return this.walletJpaRepository.findByIdOrName(id,name);
    }

    @GetMapping("/wallet/name/{name}/startingWith")
    public List<WalletJpaDto> getAllWalletsHavingNameStartingWith(@PathVariable("name") String name){
        return this.walletJpaRepository.findByNameStartingWith(name);
    }

    @GetMapping("/wallet/name/{name}/endingWith")
    public List<WalletJpaDto> getAllWalletsHavingNameEndingWith(@PathVariable("name")String name){
        return this.walletJpaRepository.findByNameEndingWith(name);
    }

    @GetMapping("/wallet/name/{name}/isNot")
    public List<WalletJpaDto> getAllWalletsHavingNameIsNot(@PathVariable("name")String name){
        return this.walletJpaRepository.findByNameIsNot(name);
    }

    @GetMapping("/custom/wallets")
    public List<WalletJpaDto> getAllWallets(){

        return this.walletJpaRepository.getAllWallets();
    }

    @GetMapping("/custom/wallets/name/{name}")
    public List<WalletJpaDto> getAllWalletsHavingNameLike(String name){

        return this.walletJpaRepository.getAllWalletsHavingNameLike("%"+name+"%");
    }

    @GetMapping("/custom/wallets/name/{name}/sorted")
    public List<WalletJpaDto> getAllWalletsHavingOrderByName(String name){
        return this.walletJpaRepository.getAllWalletsHavingOrderByName(name);
    }

    @GetMapping("/custom/wallets/balance/{balance}/sorted")
    public List<WalletJpaDto> getAllWalletsHavingBalanceOrderByBalance(Double balance){
        return this.walletJpaRepository.getAllWalletsHavingBalanceOrderByBalance(balance);
    }

    @GetMapping("/custom/wallets/{id}/greaterThanId")
    public List<WalletJpaDto> getAllWalletsHavingIdGreaterThan(@PathVariable("id")Integer id){
        return this.walletJpaRepository.getAllWalletsHavingIdGreaterThan(id);
    }
    
}
