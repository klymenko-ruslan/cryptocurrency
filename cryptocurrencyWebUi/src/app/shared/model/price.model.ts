

export class Price {
  get cny(): string {
    return this._cny;
  }

  set cny(value: string) {
    this._cny = value;
  }
  get rub(): string {
    return this._rub;
  }

  set rub(value: string) {
    this._rub = value;
  }
  get usd(): string {
    return this._usd;
  }

  set usd(value: string) {
    this._usd = value;
  }
  get eur(): string {
    return this._eur;
  }

  set eur(value: string) {
    this._eur = value;
  }
  private _usd: string;
  private _eur: string;
  private _cny: string;
  private _rub: string;
}
