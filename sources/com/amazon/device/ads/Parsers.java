package com.amazon.device.ads;

class Parsers {
    Parsers() {
    }

    public static class IntegerParser {
        private int defaultValue;
        private final MobileAdsLogger logger;
        private String parseErrorLogMessage;
        private String parseErrorLogTag;

        public IntegerParser() {
            this(new MobileAdsLoggerFactory());
        }

        IntegerParser(MobileAdsLoggerFactory loggerFactory) {
            this.logger = loggerFactory.createMobileAdsLogger("");
        }

        public IntegerParser setDefaultValue(int defaultValue2) {
            this.defaultValue = defaultValue2;
            return this;
        }

        public IntegerParser setParseErrorLogMessage(String parseErrorLogMessage2) {
            this.parseErrorLogMessage = parseErrorLogMessage2;
            return this;
        }

        public IntegerParser setParseErrorLogTag(String parseErrorLogTag2) {
            this.parseErrorLogTag = parseErrorLogTag2;
            this.logger.withLogTag(this.parseErrorLogTag);
            return this;
        }

        public int parse(String text) {
            int parsedValue = this.defaultValue;
            if (StringUtils.isNullOrWhiteSpace(text)) {
                return parsedValue;
            }
            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException e) {
                if (this.parseErrorLogTag == null || this.parseErrorLogMessage == null) {
                    return parsedValue;
                }
                this.logger.w(this.parseErrorLogMessage);
                return parsedValue;
            }
        }
    }
}
