export default interface User {
  userId: number;
  firstname: string;
  lastname: string;
  email: string;
  phoneNumber: string;
  valid: boolean;
  admin: boolean;
  role: string;
}
