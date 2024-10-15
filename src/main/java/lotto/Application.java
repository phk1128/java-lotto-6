package lotto;

import lotto.config.AppConfig;
import lotto.controller.LottoController;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        final AppConfig appConfig = new AppConfig();
        final LottoController lottoController = appConfig.lottoController();
        lottoController.run();
    }
}
