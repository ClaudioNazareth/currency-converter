package com.nazareth.currency.converter.gateways;

import static com.nazareth.currency.converter.fixture.CurrenciesTemplateLoader.VALID_CURRENCY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.nazareth.currency.converter.domains.mongo.Currencies;
import com.nazareth.currency.converter.fixture.SpringSecurityMock;
import com.nazareth.currency.converter.gateways.mongo.CurrenciesHistoryGatewayImpl;
import com.nazareth.currency.converter.gateways.mongo.repositories.CurrenciesRepository;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CurrenciesHistoryGatewayTest {

  @InjectMocks private CurrenciesHistoryGatewayImpl currenciesHistoryGateway;

  @Mock private CurrenciesRepository currenciesRepository;

  @Before
  public void setUp() throws Exception {
    SpringSecurityMock.mockUser();
    FixtureFactoryLoader.loadTemplates("com.nazareth.currency.converter.fixture");
  }

  @Test
  public void should_save_currencies() {
    Currencies currencies = Fixture.from(Currencies.class).gimme(VALID_CURRENCY);

    when(currenciesRepository.save(currencies)).thenReturn(currencies);

    final Currencies savedCurrencies =
        currenciesHistoryGateway.save(currencies, "chtnazareth@gmail.com");
    verify(currenciesRepository, times(1)).save(currencies);
    assertThat(savedCurrencies).isNotNull();
  }

  @Test
  public void should_find_top10_by_user() {
    String username = "chtnazareth@gmail.com";

    Set<Currencies> currencies = new HashSet<>();
    currencies.add(Fixture.from(Currencies.class).gimme(VALID_CURRENCY));
    currencies.add(Fixture.from(Currencies.class).gimme(VALID_CURRENCY));

    when(currenciesRepository.findTop10ByUsernameOrderByIdDesc(username)).thenReturn(currencies);

    final Set<Currencies> top10ByUserUser = currenciesHistoryGateway.findTop10ByUserUser(username);
    verify(currenciesRepository, times(1)).findTop10ByUsernameOrderByIdDesc(username);
    assertThat(top10ByUserUser).hasSize(2);
  }
}
