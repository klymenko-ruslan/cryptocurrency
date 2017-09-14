import {Wallet} from './wallet.model';

export class User {
  newPassword: string;
  password: string;
  firstName: string;
  lastName: string;
  profession: string;
  email: string;
  fiatCurrency: string;
  roles: string[];
  wallet: Wallet;

  static toUser(json: any) {
    const user = new User();
    user.password = json.password;
    user.firstName = json.firstName;
    user.lastName = json.lastName;
    user.profession = json.profession;
    user.email = json.email;
    user.fiatCurrency = json.fiatCurrency;
    user.roles = json.roles;
    user.wallet = json.wallet;
    return user;
  }
}
