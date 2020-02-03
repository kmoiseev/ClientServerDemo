import {
    API_CHAIN_FINISHED_ERRONEOUSLY,
    API_CHAIN_FINISHED_SUCCESSFULLY,
    CLEAR_API_CHAIN_INDICATION,
    START_API_CHAIN
} from "./types/ApiIndicationActionTypes";

export const startApiChain = () => ({
    type: START_API_CHAIN
});

export const apiChainFinishedSuccessfully = () => ({
    type: API_CHAIN_FINISHED_SUCCESSFULLY
});

export const apiChainFinishedErroneously = () => ({
    type: API_CHAIN_FINISHED_ERRONEOUSLY
});

export const apiChainIndicatorOff = _ => ({
    type: CLEAR_API_CHAIN_INDICATION
});