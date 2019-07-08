package com.google.pengjie.second;

public class StateMachine {
    enum State {
        START_STATE,
        INVALID_STATE,
        SIGN_STATE,
        DIGIT_STATE
    }
    private State next(State curState, char curChar) {
        switch(curState) {
            case START_STATE :
                if(curChar == '+' || curChar == '-') return State.SIGN_STATE;
                else if (curChar >= 'a' && curChar <= 'z' || curChar >= 'A' && curChar <= 'Z') return State.INVALID_STATE;
                else if (curChar >= '0' && curChar <= '9') return State.DIGIT_STATE;
                break;
            case INVALID_STATE :
                return State.INVALID_STATE;
            case SIGN_STATE:
                if (curChar >= '0' && curChar <= '9') return State.DIGIT_STATE;
                else return State.INVALID_STATE;
            case DIGIT_STATE:
                if (curChar >= '0' && curChar <= '9') return State.DIGIT_STATE;
                else return State.INVALID_STATE;
            default:
                return State.INVALID_STATE;
        }
        return State.INVALID_STATE;
    }
    int parseString(String s) {
//        boolean isPositive = true;
//        //boolean hasNumberBefore;
//        double res = 0.00;
        int result = 0;
        State curState = State.START_STATE;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            curState = next(curState, cur);
            switch(curState) {
                case START_STATE: throw new IllegalArgumentException("impossible");
                case INVALID_STATE: throw new IllegalArgumentException("illegal input");
                case DIGIT_STATE: result = result * 10 + cur - '0'; break;
                case SIGN_STATE: if (cur == '-') result *= -1; break;
                default:
                    throw new IllegalStateException("illegal state");
            }
            //check + - . 8 abc/ABC
//            if ( cur =='+' || cur == '-') {
//                if (i != 0) {
//                    throw new IllegalArgumentException("sign not correct"); //UNCHECKED exception
//                }
//                isPositive = cur == '+';
//            }
//            else if (cur == '.') {
//                if (res == 0)
//            }
//
        }
        return result;
    }
}
