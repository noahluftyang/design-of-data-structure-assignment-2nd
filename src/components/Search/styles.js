import styled from 'styled-components';

import { SearchComponent } from './Search';

export const ButtonWrapper = styled.div`
  display: flex;
  padding: 0.5rem;
  justify-content: center;

  & > *:not(:first-child) {
    margin-left: 0.5rem;
  }
`;

export const Button = styled.button`
  align-items: center;
  background-color: #fff;
  border-radius: 15px;
  display: flex;
  justify-content: center;
  padding: 0.5rem;
  width: 1.5rem;

  & > svg {
    height: 1rem;
    width: 1rem;
  }
`;

export const Search = styled(SearchComponent)`
  background-color: #3a94fb;
  padding: 1rem;
`;
