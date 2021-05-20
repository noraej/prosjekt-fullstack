import LogInUser from "./LoginUser.interface";

export default interface CreateUser extends LogInUser {
  firstname: string;
  lastname: string;
  email: string;
  password: string;
  phoneNumber: string;
  valid: boolean;
  admin: boolean;
  role: string;
}
