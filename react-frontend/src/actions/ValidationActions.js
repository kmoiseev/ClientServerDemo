import {CLEAR_VALIDATION_STATE, SET_INVALID, SET_VALID} from "./types/ValidationActionTypes";


export const markInvalid = (validationKey) => ({
    type: SET_INVALID,
    value: validationKey,
});

export const markValid = (validationKey) => ({
    type: SET_VALID,
    value: validationKey,
});

export const clearValidationState = (validationKey) => ({
    type: CLEAR_VALIDATION_STATE,
    value: validationKey,
});