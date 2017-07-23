import { CryptocurrencyWebUiPage } from './app.po';

describe('cryptocurrency-web-ui App', () => {
  let page: CryptocurrencyWebUiPage;

  beforeEach(() => {
    page = new CryptocurrencyWebUiPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
