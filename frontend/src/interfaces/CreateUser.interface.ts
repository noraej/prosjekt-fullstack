import LogInUser from "./LoginUser.interface";

export default interface SignUpUser extends LogInUser {
  firstname: string;
  lastname: string;
  email: string;
  phoneNumber: string;
  valid: boolean;
  admin: boolean;
  role: string;

}