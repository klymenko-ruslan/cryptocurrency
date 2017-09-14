
import {Address} from "./address.model";

export class Office {
  id: string;
  address: Address;

  static toOffice(json: any) {
    const office = new Office();
    office.id = json.id;
    const address = new Address();
    address.street = json.address.street;
    address.building = json.address.building;
    address.appartaments = json.address.appartaments;
    office.address = address;
    return office;
  }
}
