package com.open.cloud.designpattern.proxy;

/**
 * @author chenkechao
 * @date 2020/1/29 4:31 下午
 */
public class GamePlayerProxy implements GamePlayer {

    private GamePlayer gamePlayer;

    public GamePlayerProxy(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    @Override
    public void killBoss() {

        //before
        gamePlayer.killBoss();
        //after
    }

    @Override
    public void upGrade() {
        //before
        gamePlayer.upGrade();
        //after
    }
}
