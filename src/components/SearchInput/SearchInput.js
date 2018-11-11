import React from 'react';
import Select from 'react-select';

import { FLOORS } from './constants';
import { Title, inputStyles } from './styles';

export const SearchInputComponent = ({ className, title, ...props }) => (
  <div className={className}>
    <Title>{title}</Title>
    <Select options={FLOORS} styles={inputStyles} {...props} />
  </div>
);
