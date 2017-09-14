
export class Address {
  street: string;
  building: string;
  appartaments: string;

  static toAddress(json: any) {
    const address = new Address();
    address.street = json.street;
    address.building = json.building;
    address.appartaments = json.appartaments;
    return address;
  }
}
