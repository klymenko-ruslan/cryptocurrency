
import {Price} from './price.model';

export class Currency {
  get price(): Price {
    return this._price;
  }

  set price(value: Price) {
    this._price = value;
  }
  private _price: Price;
}
