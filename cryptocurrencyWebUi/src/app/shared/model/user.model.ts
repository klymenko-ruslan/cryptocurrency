import {Wallet} from './wallet.model';

export class User {
  private userName: string;
  private firstName: string;
  private lastName: string;
  private profession: string;
  private email: string;
  private userCurrency: string;
  private roles: string[];
  private wallet: Wallet;
}
