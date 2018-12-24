import React, { PureComponent } from 'react';
import Select from 'react-select';

import {
  BELOW_FIFTH_FLOOR,
  BELOW_FIRST_FLOOR,
  BELOW_FORTH_FLOOR,
  BELOW_SECOND_FLOOR,
  BELOW_SIXTH_FLOOR,
  BELOW_THIRD_FLOOR,
  EIGHTH_FLOOR,
  ELEVENTH_FLOOR,
  FIFTH_FLOOR,
  FIRST_FLOOR,
  FORTH_FLOOR,
  NINTH_FLOOR,
  SECOND_FLOOR,
  SEVENTH_FLOOR,
  SIXTH_FLOOR,
  TENTH_FLOOR,
  THIRD_FLOOR,
  TWELFTH_FLOOR,
} from './constants';
import { Title, inputStyles } from './styles';

export class SearchInputComponent extends PureComponent {
  groupedOptions = [
    { label: 'B6FLOOR',
      options: BELOW_SIXTH_FLOOR },
    { label: 'B5FLOOR',
      options: BELOW_FIFTH_FLOOR },
    { label: 'B4FLOOR',
      options: BELOW_FORTH_FLOOR },
    { label: 'B3FLOOR',
      options: BELOW_THIRD_FLOOR },
    { label: 'B2FLOOR',
      options: BELOW_SECOND_FLOOR },
    { label: 'B1FLOOR',
      options: BELOW_FIRST_FLOOR },
    { label: '1FLOOR',
      options: FIRST_FLOOR },
    { label: '2FLOOR',
      options: SECOND_FLOOR },
    { label: '3FLOOR',
      options: THIRD_FLOOR },
    { label: '4FLOOR',
      options: FORTH_FLOOR },
    { label: '5FLOOR',
      options: FIFTH_FLOOR },
    { label: '6FLOOR',
      options: SIXTH_FLOOR },
    { label: '7FLOOR',
      options: SEVENTH_FLOOR },
    { label: '8FLOOR',
      options: EIGHTH_FLOOR },
    { label: '9FLOOR',
      options: NINTH_FLOOR },
    { label: '10FLOOR',
      options: TENTH_FLOOR },
    { label: '11FLOOR',
      options: ELEVENTH_FLOOR },
    { label: '12FLOOR',
      options: TWELFTH_FLOOR },
  ];

  onChange = (e) => {
    const { name, onChange } = this.props;
    onChange && onChange(e, name);
  };

  render () {
    const { className, floor, onChange, title, value, ...props } = this.props;
    const selectedFloor = this.groupedOptions.find(
      (group) => group.label.split('FLOOR')[0] === floor
    );
    const selectedValue = selectedFloor && selectedFloor.options.find((node) => node.value === value);

    return (
      <div className={className}>
        <Title>{title}</Title>
        <Select
          onChange={this.onChange}
          options={this.groupedOptions}
          styles={inputStyles}
          value={selectedValue}
          {...props}
        />
      </div>
    );
  }
}
