package com.techlabs.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.techlabs.bank.error.BankErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({UsernameNotAvailableException.class})
	public ResponseEntity<BankErrorResponse> handleStudentNotFoundException(UsernameNotAvailableException exception)
	{
		BankErrorResponse error = new BankErrorResponse(HttpStatus.BAD_REQUEST.value(),exception.getMessage(),System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({CustomerNotFoundException.class})
	public ResponseEntity<BankErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException exception)
	{
		BankErrorResponse error = new BankErrorResponse(HttpStatus.BAD_REQUEST.value(),exception.getMessage(),System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({ContactNumberNotValid.class})
	public ResponseEntity<BankErrorResponse> handleContactNumberNotValid(ContactNumberNotValid exception)
	{
		BankErrorResponse error = new BankErrorResponse(HttpStatus.BAD_REQUEST.value(),exception.getMessage(),System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({BankNotFoundException.class})
	public ResponseEntity<BankErrorResponse> handleBankNotFoundException(BankNotFoundException exception)
	{
		BankErrorResponse error = new BankErrorResponse(exception.getStatus().value(),exception.getMessage(),System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({AccountNotFoundException.class})
	public ResponseEntity<BankErrorResponse> handleAccountNotFoundException(AccountNotFoundException exception)
	{
		BankErrorResponse error = new BankErrorResponse(HttpStatus.BAD_REQUEST.value(),exception.getMessage(),System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({RoleNotFoundException.class})
	public ResponseEntity<BankErrorResponse> handleRoleNotFoundException(RoleNotFoundException exception)
	{
		BankErrorResponse error = new BankErrorResponse(HttpStatus.BAD_REQUEST.value(),exception.getMessage(),System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({GenerateException.class})
	public ResponseEntity<BankErrorResponse> handleGenerateException(GenerateException exception)
	{
		BankErrorResponse error = new BankErrorResponse(HttpStatus.BAD_REQUEST.value(),exception.getMessage(),System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({NoDataFoundException.class})
	public ResponseEntity<BankErrorResponse> handleNoDataFoundException(NoDataFoundException exception)
	{
		BankErrorResponse error = new BankErrorResponse(HttpStatus.BAD_REQUEST.value(),exception.getMessage(),System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({IllegalTransactionException.class})
	public ResponseEntity<BankErrorResponse> handleIllegalTransactionException(IllegalTransactionException exception)
	{
		BankErrorResponse error = new BankErrorResponse(HttpStatus.BAD_REQUEST.value(),exception.getMessage(),System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({InvalidCredentialsException.class})
	public ResponseEntity<BankErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException exception)
	{
		BankErrorResponse error = new BankErrorResponse(HttpStatus.BAD_REQUEST.value(),exception.getMessage(),System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({UserNotFoundException.class})
	public ResponseEntity<BankErrorResponse> handleUserNotFoundException(UserNotFoundException exception)
	{
		BankErrorResponse error = new BankErrorResponse(exception.getStatus().value(),exception.getMessage(),System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
}
