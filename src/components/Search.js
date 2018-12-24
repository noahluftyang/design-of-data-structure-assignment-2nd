import React from 'react';
import styled from 'styled-components';

import { SearchInput } from './SearchInput';
/*
 * Import { ReactComponent as Elevator } from '../images/elevator.svg';
 * import { ReactComponent as Stairs } from '../images/stairs.svg';
 */

const SearchComponent = ({
  className,
  endFloor,
  endPlace,
  onChange,
  onClick,
  startFloor,
  startPlace,
  way,
}) => (
  <section className={className}>
    {/* <ButtonWrapper>
        <Button active={way === 'elevator'} value="elevator" onClick={onClick}>
          <Elevator />
        </Button>
        <Button active={way === 'stairs'} value="stairs" onClick={onClick}>
          <Stairs />
        </Button>
      </ButtonWrapper> */}
    <SearchInput
      floor={startFloor}
      name="start"
      onChange={onChange}
      placeholder="출발지 선택"
      title="출발"
      type="text"
      value={startPlace}
    />
    <SearchInput
      floor={endFloor}
      name="end"
      onChange={onChange}
      placeholder="도착지 선택"
      title="도착"
      type="text"
      value={endPlace}
    />
  </section>
);

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
