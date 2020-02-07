const centNumberToDollarString = (number) => (number / 100).toString();
const dollarStringToCentNumber = (string) => (Number(string) * 100).toFixed(0);

export const toApiSalary = (salary) => dollarStringToCentNumber(salary);
export const fromApiSalary = (salary) => centNumberToDollarString(salary);
export const fromApiEmployee = (employee) => ({
  ...employee,
  salary: centNumberToDollarString(employee.salary),
});
export const fromApiEmployeeList = (employeeList) => employeeList.map(
  fromApiEmployee,
);
