package io.mosip.preregistration.datasync.exception.util;

import java.io.IOException;

import io.mosip.kernel.core.dataaccess.exception.DataAccessLayerException;
import io.mosip.preregistration.core.common.dto.MainResponseDTO;
import io.mosip.preregistration.core.exception.InvalidRequestParameterException;
import io.mosip.preregistration.core.exception.TableNotAccessibleException;
import io.mosip.preregistration.datasync.errorcodes.ErrorCodes;
import io.mosip.preregistration.datasync.errorcodes.ErrorMessages;
import io.mosip.preregistration.datasync.exception.DataSyncRecordNotFoundException;
import io.mosip.preregistration.datasync.exception.DemographicGetDetailsException;
import io.mosip.preregistration.datasync.exception.DocumentGetDetailsException;
import io.mosip.preregistration.datasync.exception.ParseResponseException;
import io.mosip.preregistration.datasync.exception.RecordNotFoundForDateRange;
import io.mosip.preregistration.datasync.exception.ReverseDataFailedToStoreException;
import io.mosip.preregistration.datasync.exception.ZipFileCreationException;
import io.mosip.preregistration.datasync.exception.system.SystemFileIOException;

/**
 * This class is used to catch the exceptions that occur while doing Datasync
 * 
 * @author Ravi C Balaji
 * @since 1.0.0
 *
 */
public class DataSyncExceptionCatcher {
	/**
	 * Method to handle the respective exceptions
	 * 
	 * @param ex
	 *            pass the exception
	 */
	public void handle(Exception ex, MainResponseDTO<?> mainResponsedto) {
		if (ex instanceof DataAccessLayerException || ex instanceof ReverseDataFailedToStoreException) {
			throw new ReverseDataFailedToStoreException(((DataAccessLayerException) ex).getErrorCode(),
					mainResponsedto);
		} else if (ex instanceof DocumentGetDetailsException) {
			throw new DocumentGetDetailsException(((DocumentGetDetailsException) ex).getErrorCode(),
					((DocumentGetDetailsException) ex).getErrorText(), mainResponsedto);
		} else if (ex instanceof DemographicGetDetailsException) {
			throw new DemographicGetDetailsException(((DemographicGetDetailsException) ex).getErrorCode(),
					((DemographicGetDetailsException) ex).getErrorText(), mainResponsedto);
		} else if (ex instanceof InvalidRequestParameterException) {
			throw new InvalidRequestParameterException(((InvalidRequestParameterException) ex).getErrorCode(),
					((InvalidRequestParameterException) ex).getErrorText(), mainResponsedto);
		} else if (ex instanceof DataSyncRecordNotFoundException) {
			throw new DataSyncRecordNotFoundException(((DataSyncRecordNotFoundException) ex).getErrorCode(),
					((DataSyncRecordNotFoundException) ex).getErrorText(), mainResponsedto);
		} else if (ex instanceof TableNotAccessibleException) {
			throw new TableNotAccessibleException(((TableNotAccessibleException) ex).getErrorCode(),
					((TableNotAccessibleException) ex).getErrorText());
		} else if (ex instanceof ZipFileCreationException) {
			throw new ZipFileCreationException(((ZipFileCreationException) ex).getErrorCode(),
					((ZipFileCreationException) ex).getErrorText(), mainResponsedto);
		} else if (ex instanceof IOException) {
			throw new SystemFileIOException(ErrorCodes.PRG_DATA_SYNC_014.toString(),
					ErrorMessages.FILE_IO_EXCEPTION.toString(), mainResponsedto);
		} else if (ex instanceof RecordNotFoundForDateRange) {
			throw new RecordNotFoundForDateRange(((RecordNotFoundForDateRange) ex).getErrorCode(),
					((RecordNotFoundForDateRange) ex).getErrorText(), mainResponsedto);
		}
		else if (ex instanceof ParseResponseException) {
			throw new ParseResponseException(((ParseResponseException) ex).getErrorCode(), 
					((ParseResponseException) ex).getErrorText(), mainResponsedto);
		}
	}
}
