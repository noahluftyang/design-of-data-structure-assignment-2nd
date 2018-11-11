import React from 'react';

import { Input, Title } from './styles';

export const SearchInputComponent = ({ className, title, ...props }) => (
  <div className={className}>
    <Title>{title}</Title>
    <Input {...props} />
  </div>
);
