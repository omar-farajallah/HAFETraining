/**
 * Stylelint Configuration
 *
 * Documentation: https://github.com/stylelint/stylelint
 * Rules documentation: https://github.com/stylelint/stylelint/blob/master/docs/user-guide/rules.md
 */
module.exports = {
    'ignoreFiles': 'docs/**/*.css',
    'rules': {

        // https://github.com/stylelint/stylelint/blob/master/lib/rules/indentation/README.md
        'indentation': 4,

        // https://github.com/stylelint/stylelint/blob/master/lib/rules/selector-list-comma-newline-after/README.md
        'selector-list-comma-newline-after': 'always',

        // https://github.com/stylelint/stylelint/blob/master/lib/rules/value-list-comma-space-after/README.md
        'value-list-comma-space-after': 'always',

        'function-comma-space-after': 'always',

        // https://github.com/stylelint/stylelint/blob/master/lib/rules/declaration-colon-space-after/README.md
        'declaration-colon-space-after': 'always',

        // https://github.com/stylelint/stylelint/blob/master/lib/rules/block-opening-brace-space-before/README.md
        'block-opening-brace-space-before': 'always',

        // https://github.com/stylelint/stylelint/blob/master/lib/rules/function-url-quotes/README.md
        'function-url-quotes': 'never',

        // https://github.com/stylelint/stylelint/blob/master/lib/rules/block-no-empty/README.md
        'block-no-empty': true,

        // https://github.com/stylelint/stylelint/blob/master/lib/rules/block-no-single-line/README.md
        'block-no-single-line': true,

        // https://github.com/stylelint/stylelint/blob/master/lib/rules/selector-max-compound-selectors/README.md
        'selector-max-compound-selectors': [
            3,
            {
                'message': 'Please limit the use of compound selectors',
                'severity': 'warning'
            }
        ],

        // https://github.com/stylelint/stylelint/blob/master/lib/rules/max-nesting-depth/README.md
        'max-nesting-depth': 1,

        // https://github.com/stylelint/stylelint/blob/master/lib/rules/declaration-no-important/README.md
        'declaration-no-important': true,

        // https://github.com/stylelint/stylelint/blob/master/lib/rules/stylelint-disable-reason/README.md
        'stylelint-disable-reason': 'always-after'
    }
};