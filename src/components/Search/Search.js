import React from 'react';

import { Button, ButtonWrapper } from './styles';
import { SearchInput } from '../SearchInput';
import { ReactComponent as Elevator } from '../../images/elevator.svg';
import { ReactComponent as Stairs } from '../../images/stairs.svg';

export const SearchComponent = ({ className, way, onClick }) => {
  return (
    <section className={className}>
      <ButtonWrapper>
        <Button active={way === 'elevator'} value="elevator" onClick={onClick}>
          <Elevator />
        </Button>
        <Button active={way === 'stairs'} value="stairs" onClick={onClick}>
          <Stairs />
        </Button>
      </ButtonWrapper>
      <SearchInput placeholder="층 선택" title="출발" type="text" />
      <SearchInput placeholder="층 선택" title="도착" type="text" />
    </section>
  );
};
