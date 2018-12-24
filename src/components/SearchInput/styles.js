import styled from 'styled-components';

import { SearchInputComponent } from './SearchInput';

export const SearchInput = styled(SearchInputComponent)`
  align-items: center;
  background-color: #4c9dfc;
  border: 1px solid #3a94fb;
  display: flex;
  padding: 0.5rem;

  & > *:not(:first-child) {
    margin-left: 0.5rem;
  }
`;

export const Title = styled.h5`
  color: #fff;
`;

export const inputStyles = {
  container: (provided) => ({
    ...provided,
    flex: 1,
  }),
  control: (provided) => ({
    ...provided,
    backgroundColor: 'transparent',
    border: 0,
  }),
  dropdownIndicator: (provided) => ({
    ...provided,
    padding: '0 8px',
  }),
  input: (provided) => ({
    ...provided,
    color: '#fff',
  }),
  placeholder: (provided) => ({
    ...provided,
    color: '#80d1fd',
  }),
  singleValue: (provided) => ({
    ...provided,
    color: '#fff',
  }),
};

export const Input = styled.input`
  background-color: transparent;
  border: 0;
  color: #fff;
  flex: 1;

  &:focus {
    outline: 0;
  }

  &::placeholder {
    color: #80d1fd;
  }
`;
